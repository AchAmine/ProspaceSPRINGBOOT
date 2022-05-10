package com.prospace.spring.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Comment;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PostRepository;
import com.prospace.spring.repository.Post_CommentRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServicePost_Comment implements IServicePost_Comment {


	@Autowired
	Post_CommentRepository post_commentRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void deleteComment(Long id) {
		// TODO Auto-generated method stub
		Post_Comment comment = post_commentRepository.findById(id).orElse(null);
		post_commentRepository.delete(comment);
	}

	@Override
	public Post_Comment updateComment(Post_Comment comment) {
		Post_Comment post_comment = post_commentRepository.findById(comment.getIdComment()).orElse(null);
		// TODO Auto-generated method stub
		comment.setPost(post_comment.getPost());
		comment.setUser(post_comment.getUser());
		Date date = new Date(System.currentTimeMillis());
		comment.setUpdatedAt(date);
		return post_commentRepository.save(comment);

	}

	@Override
	public List<Post_Comment> retrievePostComments(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		List<Post_Comment> comments = post_commentRepository.findByPost(post);

		return comments;
	}

	/****************************************************/

	private static int largestWordLength = 0;

	private static Map<String, String[]> allBadWords = new HashMap<String, String[]>();

	@Override
	public String GetCensoredText(String content) {
		loadBadWords();
		if (content == null) {
			return " ";
		}

		String modifiedInput = content;

		// remove leetspeak
		modifiedInput = modifiedInput.replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e")
				.replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t")
				.replaceAll("0", "o").replaceAll("9", "g");

		// ignore any character that is not a letter
		modifiedInput = modifiedInput.toLowerCase().replaceAll("[^a-z A-Z]", "*");

		ArrayList<String> badWordsFound = new ArrayList<>();

		// iterate over each letter in the word
		for (int start = 0; start < modifiedInput.length(); start++) {
			// from each letter, keep going to find bad words until either the end of
			// the sentence is reached, or the max word length is reached.
			// The OFFSET argument is used to identify the starting point to return rows
			for (int offset = 1; offset < (modifiedInput.length() + 1 - start)
					&& offset < largestWordLength; offset++) {
				String wordToCheck = modifiedInput.substring(start, start + offset);
				if (allBadWords.containsKey(wordToCheck)) {
					String[] ignoreCheck = allBadWords.get(wordToCheck);
					boolean ignore = false;
					for (int stringIndex = 0; stringIndex < ignoreCheck.length; stringIndex++) {
						if (modifiedInput.contains(ignoreCheck[stringIndex])) {
							// if words found to check ignre = true
							ignore = true;
							break;

						}
					}

					if (!ignore) {
						badWordsFound.add(wordToCheck);
					}
				}
			}
		}
		String inputToReturn = modifiedInput;// content
		for (String swearWord : badWordsFound) {
			char[] charsStars = new char[swearWord.length()];
			Arrays.fill(charsStars, '*');
			final String stars = new String(charsStars);

			// The "(?i)" is to make the replacement case insensitive.
			inputToReturn = inputToReturn.replaceAll("(?i)" + swearWord, stars);
		}

		return inputToReturn;
	} // end getCensoredText

	@Override
	public void loadBadWords() {
		int readCounter = 0;
		try {
			FileReader fr = new FileReader("C:\\badwords.txt");
			BufferedReader reader = new BufferedReader(fr);
			String currentLine = "";
			while ((currentLine = reader.readLine()) != null) {
				readCounter++;
				String[] content = null;
				try {
					if (1 == readCounter) {
						continue;
					}

					content = currentLine.split(",");
					if (content.length == 0) {
						continue;
					}

					final String word = content[0];

					if (word.startsWith("-----")) {
						continue;
					}

					if (word.length() > largestWordLength) {
						largestWordLength = word.length();
					}

					String[] ignore_in_combination_with_words = new String[] {};
					if (content.length > 1) {
						ignore_in_combination_with_words = content[1].split("_");
					}

					// Make sure there are no capital letters in the spreadsheet
					allBadWords.put(word.toLowerCase(), ignore_in_combination_with_words);
				} catch (Exception except) {
				}
			} // end while
		} catch (IOException except) {
		}
	} // end loadBadWords

	@Override
	public Post_Comment addComment(Long userId, Long postId, Post_Comment comment) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		comment.setUser(user);
		comment.setPost(post);
		String str = GetCensoredText(comment.getContent());
		/*
		 * String str= comment.getContent(); str=GetCensoredText(str);
		 */
		comment.setContent(str);
		Date date = new Date(System.currentTimeMillis());
		comment.setPostedAt(date);

		return post_commentRepository.save(comment); 
	}

	/****** comment reply ****/

	@Override
	public Post_Comment addCommentReply(Long commentId, Long userId, Post_Comment comment) {
		User user = userRepository.findById(userId).orElse(null);
		Post_Comment pComm = post_commentRepository.findById(commentId).orElse(null);
		comment.setPComment(pComm);
		comment.setUser(user);
		return post_commentRepository.save(comment);
	}

	@Override
	public List<Post_Comment> retrievePostCommentReplies(Long commentId) {
		Post_Comment postComment = post_commentRepository.findById(commentId).orElse(null);
		List<Post_Comment> comments = post_commentRepository.findBypComment(postComment);

		return comments;
	}

	/****************************************/

}
