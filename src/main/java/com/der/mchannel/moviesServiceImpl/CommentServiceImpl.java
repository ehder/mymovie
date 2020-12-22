package com.der.mchannel.moviesServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.der.mchannel.entity.Comment;
import com.der.mchannel.moviesService.CommentService;
import com.der.mchannel.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repo;

	@Override
	public void addComment(Comment comment) {
		repo.save(comment);
	}

}
