package com.prospace.spring.configuration;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.prospace.spring.batch.OfferProcessor;
import com.prospace.spring.batch.OfferWriter;
import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.OfferState;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class OfferBatchConfig {

	
	private static final String JOB_NAME = "listOffersJob";
	private static final String STEP_NAME = "processingStep";
	private static final String READER_NAME = "offerItemReader";

	/*5. Lister les champs que nous souhaitons parser dans le
	 * fichier excel : nom1,nom2,nom3,...*/
	private String names = "title,description,startsAt,endsAt,PartnerEmail";
	/*6. Définir la stratégie de délimitation des différents champs*/
	private String delimiter = ",";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	
	
	

	/*7 Créer le bean associé au job et le lancer*/
	@Bean
	public Job listOffersJob(Step step1) {
		return jobBuilderFactory.get(JOB_NAME).start(step1).build();
	}

	/*8 Créer le step associé au job et le lancer*/
	@Bean
	public Step offerStep() {
		return stepBuilderFactory.get(STEP_NAME).<Offer, Offer>chunk(5).reader(multiResourceItemReader())
				.processor(offerItemProcessor()).writer(offerItemWriter()).build();
	}
	
	/*9. étape 1 (ItemReader) Créer le reader pour récupérer les données depuis
	 * le fichier csv*/
	
	// ----------------------
	 @Bean
     public MultiResourceItemReader<Offer> multiResourceItemReader() {
       MultiResourceItemReader<Offer> resourceItemReader = new MultiResourceItemReader<Offer>();
           ClassLoader cl = this.getClass().getClassLoader();
           ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);

       Resource[] resources ;
	try {
		resources = resolver.getResources("*.csv");
		resourceItemReader.setResources(resources);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       resourceItemReader.setDelegate(reader());
       return resourceItemReader;
     }

     @Bean
     public FlatFileItemReader<Offer> reader() {
       FlatFileItemReader<Offer> reader = new FlatFileItemReader<Offer>();
       
       reader.setName(READER_NAME);
       reader.setLinesToSkip(1);
       reader.setLineMapper(offerLineMapper());
       return reader;
     }

	// ----------------------
	/*10. récupérer les données ligne par ligne du fichier excel */

	@Bean
	public LineMapper<Offer> offerLineMapper() {

		final DefaultLineMapper<Offer> defaultLineMapper = new DefaultLineMapper<>();
		final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(delimiter);
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(names.split(delimiter));
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSet -> {
			Offer data = new Offer();
			data.setTitle(fieldSet.readString(0));
			data.setDescription(fieldSet.readString(1));
			data.setStartsAt(fieldSet.readDate(2));
			data.setEndsAt(fieldSet.readDate(3));
			data.setPartnerEmail(fieldSet.readString(4));
			return data;
		});
		return defaultLineMapper;
	}

	/* 11. étape 2 (ItemProcessor) fait appel à la classe OfferProcessor
	 * qui se charge de modifier la logique des données selon
	 * nos besoins métiers */
	@Bean
	public ItemProcessor<Offer, Offer> offerItemProcessor() {
		return new OfferProcessor();
	}

	
	/* 12. étape 3 (ItemWriter) fait appel à la classe OfferWriter
	 * qui se charge de lancer le service de sauvegarde des 
	 * données liées à la partie offer dans la BD*/
	@Bean
	public ItemWriter<Offer> offerItemWriter() {
		return new OfferWriter();
	}
	
}

