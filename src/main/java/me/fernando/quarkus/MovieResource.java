package me.fernando.quarkus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/movies")
public class MovieResource {

    public static List<Movie> movies;

    static {
        movies = new ArrayList<>();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        return Response.ok(movies).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMovie(Movie movie) {
        movies.add(movie);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(Movie movieToUpdate) {

        movies = movies.stream().peek(movie -> {
            if (movie.getId().equals(movieToUpdate.getId())) {
                movie.setName(movieToUpdate.getName());
            }
        }).collect(Collectors.toList());

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Response deleteMovie(@PathParam("id") Long id) {
        Optional<Movie> movieToDelete = movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();

        boolean removed = false;
        if (movieToDelete.isPresent()) {
            removed = movies.remove(movieToDelete.get());
        }

        final Response.Status status = removed ?
                Response.Status.NO_CONTENT : Response.Status.BAD_REQUEST;

        return Response.status(status).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public int countMovies() {
        return movies.size();
    }
}
