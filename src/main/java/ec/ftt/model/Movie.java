package ec.ftt.model;


public class Movie {
	private long id;
	private String name;
	private String producer;
	private String genre;
	private String releaseDate;
	private int movieDuration;
	public Movie() {

	}
	public Movie(Long id, String name, String producer, String genre, String releaseDate, int movieDuration) {
		super();
		setId(id);
		setName(name);
		setProducer(producer);
		setGenre(genre);
		setReleaseDate(releaseDate);
		setMovieDuration(movieDuration);
	}
	public Movie(String name, String producer, String genre, String releaseDate, int movieDuration) {
		super();
		setName(name);
		setProducer(producer);
		setGenre(genre);
		setReleaseDate(releaseDate);
		setMovieDuration(movieDuration);
	}

	public int getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

}
