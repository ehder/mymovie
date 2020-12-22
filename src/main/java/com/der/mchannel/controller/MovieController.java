package com.der.mchannel.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.der.mchannel.entity.Account;
import com.der.mchannel.entity.Comment;
import com.der.mchannel.entity.Movies;
import com.der.mchannel.moviesServiceImpl.CommentServiceImpl;
import com.der.mchannel.moviesServiceImpl.DirectorServiceImpl;
import com.der.mchannel.moviesServiceImpl.MoviesServiceImpl;
import com.der.mchannel.pagination.PageList;
import com.der.mchannel.repository.UserAccountRepository;

@Controller
public class MovieController {

	private static final int INIT_PAGE_SIZE = 16;
	private static final int INIT_PAGE_INDEX = 0;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@Autowired
	private MoviesServiceImpl movieService;

	@Autowired
	private DirectorServiceImpl directorService;

	@Autowired
	private UserAccountRepository userRepo;

	@Autowired
	private CommentServiceImpl commentsService;

	@RequestMapping(value = "/movieDetails", method = RequestMethod.GET)
	public String addComment(Model model, @RequestParam("code") String code) {

		Movies movie = movieService.findMovieByCode(code);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String principal = authentication.getName();

		if (movie != null) {
			model.addAttribute("movie", movie);
			Account user = userRepo.findAccountByuserName(principal);
			if (user != null) {
				Comment comment = new Comment();
				comment.setAccount(user);
				comment.setMovie(movie);
				model.addAttribute("addcomment", comment);
			}
			return "movieDetails";
		}
		return "movieDetails";
	}

	@RequestMapping(value = "/movieDetails", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("addcomment") Comment comment, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/movieDetails?code" + "=" + comment.getMovie().getCode();
		}
		if (comment.getAccount() != null) {
			commentsService.addComment(comment);
		}
		return "redirect:/movieDetails?code" + "=" + comment.getMovie().getCode();
	}

	// show all movies
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllMoviesAndSeries(@RequestParam("parapageSize") Optional<Integer> pageSize,
			@RequestParam("parapage") Optional<Integer> page, Model model) {

		int evalPageSize = pageSize.orElse(INIT_PAGE_SIZE);
		int evalPageIndex = (page.orElse(0) < 1) ? INIT_PAGE_INDEX : page.get() - 1;

		PageList<Movies> pagedList = new PageList<>(evalPageSize, evalPageIndex);
		List<Movies> userList = movieService.findAllMovies(evalPageSize, pagedList.getPageOffset());
		int totalCount = movieService.getMoviesCount();
		pagedList.setItemList(userList);
		pagedList.setTotalCount(totalCount);

		model.addAttribute("list", pagedList);
		model.addAttribute("pageSizes", PAGE_SIZES);
		pagedList.getTotalPageCount();

		return "movieList";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("keyword") String keyword, Model model) {
		
		List<Movies> listMovies = movieService.search(keyword);
		model.addAttribute("listMovies", listMovies);
		model.addAttribute("keyword", keyword);

		return "search";
	}

	@RequestMapping(value = "/movieImage", method = RequestMethod.GET)
	public void movieImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("code") String code) throws IOException {
		Movies movie = null;
		if (code != null) {
			movie = this.movieService.findMovieByCode(code);
		}

		if (movie != null && movie.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(movie.getImage()); 
		}
		response.getOutputStream().close();

	}

	@RequestMapping(value = "/showStar")
	public String getMovieByStar(Model model) {
		// TODO
		return "showStar";
	}

	@RequestMapping(value = "/showDirector")
	public String getMovieDirector(Model model) {
		// TODO
		return "showDirector";
	}

	@RequestMapping(value = "/showGeneres")
	public String getMovieGeneres(Model model) {
		// TODO
		return "showGeneres";
	}

	@RequestMapping(value = "/showMovies")
	public String getMovies(Model model) {
		// TODO
		return "showMovies";
	}

	@RequestMapping(value = "/showSeries")
	public String getSeries(Model model) {
		// TODO
		return "showSeries";
	}

	/*
	 * @GetMapping(value="/index") public String getMovieByDate(Model model) {
	 * LocalDate localDate = LocalDate.now(); DateTimeFormatter formatD =
	 * DateTimeFormatter.ofPattern("yyyy"); String formatDate =
	 * localDate.format(formatD); System.out.println(formatDate);
	 * 
	 * int startDate = LocalDate.of(2012, 01, 01).getMonthValue(); int endDate =
	 * LocalDate.of(2012, 12, 31).getMonthValue(); System.out.println(startDate +
	 * " : " + endDate);
	 * 
	 * 
	 * switch (2013) { case 2012: int start = startDate; break; default: break; }
	 * 
	 * 
	 * 
	 * //model.addAttribute("dates",formatDate);
	 * 
	 * return "index"; }
	 */

	public MoviesServiceImpl getMovieService() {
		return movieService;
	}

	public void setMovieService(MoviesServiceImpl movieService) {
		this.movieService = movieService;
	}

	public DirectorServiceImpl getDirectorService() {
		return directorService;
	}

	public void setDirectorService(DirectorServiceImpl directorService) {
		this.directorService = directorService;
	}

}
