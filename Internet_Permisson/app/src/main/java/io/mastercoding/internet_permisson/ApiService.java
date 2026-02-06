package io.mastercoding.internet_permisson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // GET https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    Call<List<Post>> getPosts();

    // Examples for later (just FYI):
    // @GET("posts/{id}") Call<Post> getPost(@Path("id") int id);
    // @GET("posts") Call<List<Post>> getPostsByUser(@Query("userId") int userId);
    // @POST("posts") Call<Post> createPost(@Body Post post);
}