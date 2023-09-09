# MovieZJCApp
An android app built using Jetpack Compose that consumes TMDB API to display movies App with clean Architecture😉😎

![644b72064bdda086e334a4ad_movie-streaming-1](https://github.com/meshramaravind/MovieZJCApp/assets/25154589/a442b4af-66d3-4d9f-92d3-8feac8cf00a3)

![644b720514dc8f0774fdba42_movie-streaming-2](https://github.com/meshramaravind/MovieZJCApp/assets/25154589/40d0fad2-245f-463f-94d6-5c8767ce54dd)

![644b7206a165c917de4245b4_movie-streaming-3](https://github.com/meshramaravind/MovieZJCApp/assets/25154589/30234962-f754-4c8e-afa1-08f2e2671044)

# Setup Requirements
First, obtain your API key from TMDB and add it in a file named apikey.properties within the root directory:
YOUR_API_KEY="****"


Then, replace it in the build.gradle(:app) :
...
buildConfigField("String", "YOUR_API_KEY", apikeyProperties['YOUR_API_KEY'])

Finally, rebuild the project for changes to take effect and don't forget to update the references of this key in the APIService.kt file.
...
@Query("api_key") apiKey: String = BuildConfig.YOUR_API_KEY,


►Design Credit: https://www.dhiwise.com/templates/jusplay-movie-streaming-app-flutter

Some features are coming soon.

