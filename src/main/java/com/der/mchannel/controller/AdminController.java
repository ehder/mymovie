package com.der.mchannel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.der.mchannel.entity.Directors;
import com.der.mchannel.entity.Generes;
import com.der.mchannel.entity.Movies;
import com.der.mchannel.entity.Stars;
import com.der.mchannel.form.MoviesForm;
import com.der.mchannel.moviesServiceImpl.CastServiceImpl;
import com.der.mchannel.moviesServiceImpl.DirectorServiceImpl;
import com.der.mchannel.moviesServiceImpl.GeneresServiceImpl;
import com.der.mchannel.moviesServiceImpl.MoviesServiceImpl;
import com.der.mchannel.validator.MovieFormValidator;
import com.der.mchannel.validator.StarFormValidator;

@Controller
public class AdminController {

	@Autowired
	private MoviesServiceImpl moviesService;

	@Autowired
	private DirectorServiceImpl directorService;

	@Autowired
	private GeneresServiceImpl gService;

	@Autowired
	private CastServiceImpl starService;
	
	@Autowired
	private StarFormValidator starFormValidator;
	
	@Autowired
	private MovieFormValidator movieFormValidator;
	
	@InitBinder
	public void myInitBiner(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		
		//validation for star class
		if (target.getClass() == Stars.class) {
			dataBinder.setValidator(starFormValidator);
		}
		
		//validation for movieForm class
		if (target.getClass() == MoviesForm.class) {
			dataBinder.setValidator(movieFormValidator);
		}
	}

	@RequestMapping("/admin/login")
	public String home() {
		return "login";
	}

	// add Director
	@RequestMapping(value = { "/admin/director" }, method = RequestMethod.GET)
	public String addDirector(Model model) {
		Directors director = new Directors();
		List<Directors> directorList = directorService.findAllDirector();
		model.addAttribute("directorList",directorList);
		model.addAttribute("director", director);
		return "director";

	}

	@RequestMapping(value = { "/admin/director" }, method = RequestMethod.POST)
	public String addDirector(@Valid @ModelAttribute("director") Directors director, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "director";
		}
		directorService.addDirectors(director);
		return "redirect:/admin/director";
	}
	@RequestMapping(value = { "/admin/director/delete" })
	public String deleteDirector(@RequestParam("id")Integer id) {
		directorService.deleteDirectorById(id);
		return "redirect:/admin/director";
	}

	// add Generes
	@RequestMapping(value = { "/admin/generes" }, method = RequestMethod.GET)
	public String addGeneres(Model model) {
		Generes generes = new Generes();
		List<Generes> gList = gService.findAllGeneres();
		model.addAttribute("generesList",gList);
		model.addAttribute("generes",generes);
		return "generes";

	}

	@RequestMapping(value = { "/admin/generes" }, method = RequestMethod.POST)
	public String addGeneres(@Valid @ModelAttribute("generes") Generes generes, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "generes";
		}
		gService.addGeneres(generes);
		return "redirect:/admin/generes";
	}
	
	@RequestMapping(value = { "/admin/generes/delete" })
	public String deleteGeneres(@RequestParam("id")Integer id) {
		gService.deleteGeneresById(id);
		return "redirect:/admin/generes";
	}
	
	// add cast
		@RequestMapping(value = { "/admin/cast" }, method = RequestMethod.GET)
		public String addCast(@RequestParam(value = "name", defaultValue = "")String name,Model model) {
			Stars star = new Stars();
			
			//show all star in table
			List<Stars> starList = starService.findAllStar();
			model.addAttribute("starList",starList);
			
			model.addAttribute("star", star);
			return "cast";

		}

		@RequestMapping(value = { "/admin/cast" }, method = RequestMethod.POST)
		public String addCast(@Valid @ModelAttribute("star") Stars star, BindingResult result, Model model) {
			
			
			if (result.hasErrors()) {
				List<Stars> starList = starService.findAllStar();
				model.addAttribute("starList",starList);
				return "cast";
			}
				if (star != null) {
					starService.addStar(star);
				}
			
				
			
			
			
			return "redirect:/admin/cast";
		}
		
		@RequestMapping(value = { "/admin/cast/delete" })
		public String deleteCast(@RequestParam("id")Integer id) {
			starService.deleteCast(id);
			return "cast";
		}

	// add movie
	@RequestMapping(value = { "/admin/movie" }, method = RequestMethod.GET)//need defaultValue because of create new movie
	public String movie(Model model,@RequestParam(value = "code", defaultValue = "")String code) {
		
		//declare movieForm
		MoviesForm moviesForm = null;
		
		//if code have, find movie by code and create moviesForm object
		if (code != null && code.length() > 0) {
			Movies movie = moviesService.findMovieByCode(code);
			
			
			if (movie.getCode() != null) {
				moviesForm = new MoviesForm(movie);
				System.out.println(moviesForm + " : MoviesForm(movie)");
			}
		}
		
		//if code doesn't have, create movieForm and set it to true
		if (moviesForm == null) {
			moviesForm = new MoviesForm();
			System.out.println(moviesForm + " : MoviesForm");
			moviesForm.setNewMovie(true);
		}
		
		model.addAttribute("director", directorService.findAllDirector());
		model.addAttribute("generes", gService.findAllGeneres());
		model.addAttribute("stars", starService.findAllStar());
		model.addAttribute("moviesForm", moviesForm);
		
		//For movieListDetailsTable
		model.addAttribute("movieList", moviesService.findAllMovies());
		
		return "movie";
	}
	

	@RequestMapping(value = { "/admin/movie" }, method = RequestMethod.POST)
	public String movieSave( @ModelAttribute("moviesForm") @Valid
	MoviesForm moviesForm, BindingResult result,Model model) {

		if (result.hasErrors()) {
			return "movie";
		}
		
		
		moviesService.save(moviesForm);
		return "redirect:/admin/movie";   
	}
	
	@RequestMapping(value = { "/admin/movieListTable" }, method = RequestMethod.GET)
	public String listMovie(Model model) {
		
		List<Movies> movieList = moviesService.findAllMovies();

		List<Directors> dList = directorService.findAllDirector();
		List<Generes> generesList = gService.findAllGeneres();
		List<Stars> starList = starService.findAllStar();

		model.addAttribute("movieList", movieList);
		model.addAttribute("director", dList);
		model.addAttribute("generes", generesList);
		model.addAttribute("stars", starList);
		
		return "movieListDetailsTable";
	}
	
	@RequestMapping(value = "/terms")
	public String terms(Model model) {
		return"terms";
	}
	
	/*//Update or Edit Post
	@RequestMapping(value = "/admin/editMovie", method = RequestMethod.GET)
	public String update(Model model,@RequestParam("code")String code) {
		MoviesForm moviesForm = new MoviesForm();
		//Movies movie = moviesService.findMovieByCode(code);
		model.addAttribute("moviesForm", moviesForm);
		return "admin/movie";
	}
	
	@RequestMapping(value = "/admin/editMovie", method = RequestMethod.POST )
	public String update(@RequestParam("code")String code,Movies movies,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			movies.setCode(code);
			return "admin/movie";
		}
		moviesService.saveMovie(movies);
		
		return "admin/movie";
	}*/
	

	@RequestMapping(value = { "/admin/movie/delete" })
	public String deleteMovie(@RequestParam(value = "code")String code) {
		Movies movie = moviesService.findMovieByCode(code);
		moviesService.deleteMovie(movie);
		return "movieListDetailsTable";
	}
	


}