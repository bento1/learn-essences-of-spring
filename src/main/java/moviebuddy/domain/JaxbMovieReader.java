package moviebuddy.domain;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import moviebuddy.ApplicationException;
import moviebuddy.Movie;

public class JaxbMovieReader implements MovieReader{

    @Override
    public List<Movie> loadMovies() {
        // TODO Auto-generated method stub
        try{
            final JAXBContext jaxb= JAXBContext.newInstance(MovieMetaData.class);
            final Unmarshaller unmarchaller=jaxb.createUnmarshaller();//checked exception 발생하기떄문에 적절히 감싸서 runtime exception으로 만들어줘야함
            final InputStream content = ClassLoader.getSystemResourceAsStream("movie_metadata.xml");
            final Source source = new StreamSource(content);
            final MovieMetaData metadata=(MovieMetaData) unmarchaller.unmarshal(source);
            return metadata.toMovies();
        }catch(JAXBException error){
            throw new ApplicationException("failed to load movies data",error);
        }

        
    }
    //xml to java  할려면 각 <> 마다 클래스가 있어야함 static인가봄..? getter, setter 생성
    @XmlRootElement(name="moviemetadata") //xml의 root elem가 뭔지 알려줘야함
    public static class MovieMetaData{
        private List<MovieData> movies;
            /**
         * @return List<MovieData> return the movies
         */
        public List<MovieData> getMovies() {
            return movies;
        }

        public List<Movie> toMovies() {
                // return movies.stream().map(it->it.toMovie()).collect(Collectors.toList()); // 람다식
                return movies.stream().map(MovieData::toMovie).collect(Collectors.toList()); //메소드 레퍼런스를 넘겨주는 방법
            }

        /**
         * @param movies the movies to set
         */
        public void setMovies(List<MovieData> movies) {
            this.movies = movies;
        }
        
    }
    public static class MovieData{
        private String title;
        private List<String> geners;
        private String language;
        private String country;
        private int releaseYear;
        private String director;
        private List<String> actors;
        private URL imdbLink;
        private String watchedDate;
            /**
         * @return String return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title the title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return List<String> return the geners
         */
        public List<String> getGeners() {
            return geners;
        }

        /**
         * @param geners the geners to set
         */
        public void setGeners(List<String> geners) {
            this.geners = geners;
        }

        /**
         * @return String return the language
         */
        public String getLanguage() {
            return language;
        }

        /**
         * @param language the language to set
         */
        public void setLanguage(String language) {
            this.language = language;
        }

        /**
         * @return String return the country
         */
        public String getCountry() {
            return country;
        }

        /**
         * @param country the country to set
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         * @return int return the releaseYear
         */
        public int getReleaseYear() {
            return releaseYear;
        }

        /**
         * @param releaseYear the releaseYear to set
         */
        public void setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
        }

        /**
         * @return String return the director
         */
        public String getDirector() {
            return director;
        }

        /**
         * @param director the director to set
         */
        public void setDirector(String director) {
            this.director = director;
        }

        /**
         * @return List<String> return the actors
         */
        public List<String> getActors() {
            return actors;
        }

        /**
         * @param actors the actors to set
         */
        public void setActors(List<String> actors) {
            this.actors = actors;
        }

        /**
         * @return URL return the imdbLink
         */
        public URL getImdbLink() {
            return imdbLink;
        }

        /**
         * @param imdbLink the imdbLink to set
         */
        public void setImdbLink(URL imdbLink) {
            this.imdbLink = imdbLink;
        }

        /**
         * @return String return the watchedDate
         */
        public String getWatchedDate() {
            return watchedDate;
        }

        /**
         * @param watchedDate the watchedDate to set
         */
        public void setWatchedDate(String watchedDate) {
            this.watchedDate = watchedDate;
        }
        public Movie toMovie(){
            String title=getTitle();
            List<String> geners=getGeners();
            String language = getLanguage();
            String country = getCountry();
            int releaseYear=getReleaseYear();
            String director = getDirector();
            List<String> actors= getActors();
            URL imdbLink=getImdbLink();
            String watchedDate= getWatchedDate();
            return Movie.of(title,geners,language,country,releaseYear,director,actors,imdbLink,watchedDate);
        }
    }





}