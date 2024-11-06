package com.moviecatalog.app.data

import com.moviecatalog.app.R
import com.moviecatalog.app.domain.model.Movie
import com.moviecatalog.app.domain.model.MovieYear
import com.moviecatalog.app.domain.model.Moviecategory
import com.moviecatalog.app.domain.model.Movies

class MovieDataSourceImpl:MovieDataSource {

    override suspend fun getData(): Movie {
        return Movie(prepareDataForMovies())
    }

    private suspend fun prepareDataForMovies(): List<Moviecategory> {

        val movieList = arrayListOf<Moviecategory>().apply {
            add(
                Moviecategory(
                    id = MovieYear.Movie2024,
                    movieList = getMovieListToView(MovieYear.Movie2024, R.drawable.birdman),
                    MovieName = R.string.movie2024,
                    image = R.drawable.birdman
                )
            )
            add(
                Moviecategory(
                    id = MovieYear.Movie2023,
                    movieList = getMovieListToView(MovieYear.Movie2023, R.drawable.moonlight),
                    MovieName = R.string.movie2023,
                    image = R.drawable.moonlight
                )
            )

            add(
                Moviecategory(
                    id = MovieYear.Movie2022,
                    movieList = getMovieListToView(MovieYear.Movie2022, R.drawable.nomadland),
                    MovieName = R.string.movie2022,
                    image = R.drawable.nomadland
                )
            )

            add(
                Moviecategory(
                    id = MovieYear.Movie2021,
                    movieList = getMovieListToView(MovieYear.Movie2021, R.drawable.shape1),
                    MovieName = R.string.movie2021,
                    image = R.drawable.shape1
                )
            )

            add(
                Moviecategory(
                    id = MovieYear.Movie2020,
                    movieList = getMovieListToView(MovieYear.Movie2020, R.drawable.viggo),
                    MovieName = R.string.movie2020,
                    image = R.drawable.viggo
                )
            )


        }
        return movieList
    }

    override suspend fun getMovieListToView(movieYear: MovieYear, icon: Int) =
        movieYear.getMoviesIcon(icon)

    override suspend fun filterList(index: Int, input: String) =
        Movie(getData().movieList.toMutableList().apply {
            this[index] = this[index].copy(movieList = getData().movieList[index].movieList.filter {
                it.title.contains(
                    input,
                    true
                )
            })
        })

    override suspend fun getStatisticCount(list: List<Movies>): List<Map.Entry<Char, Int>> {
        val MAX_VALUE_CHAR = 3
        val listWithoutWhiteSpaces = list.joinToString("") {
            it.title.filterNot { char ->
                char.isWhitespace()
            }
        }

        val topChar = listWithoutWhiteSpaces.groupingBy { it }
            .eachCount().entries.sortedByDescending { it.value }
        return topChar.take(MAX_VALUE_CHAR)
    }


    private fun MovieYear.getMoviesIcon(icon: Int): List<Movies> {
        val moviesList =
            when (this) {
                MovieYear.Movie2024 ->
                    listOf(
                        "Inside Out 2",
                        "Deadpool & Wolverine",
                        "Despicable Me 4",
                        "Beetlejuice Beetlejuice",
                        "Twisters",
                        "Godzilla x Kong: The New Empire",
                        "Kung Fu Panda 4",
                        "Bad Boys: Ride or Die",
                        "Kingdom of the Planet of the Apes",
                        "Night Swim",
                    )

                MovieYear.Movie2023 ->
                    listOf(
                        "Barbie",
                        "The Super Mario Bros. Movie",
                        "Spider-Man: Across the Spider-Verse",
                        "Guardians of the Galaxy Vol. 3",
                        "Oppenheimer",
                        "The Little Mermaid",
                        "Wonka",
                        "Ant-Man and the Wasp: Quantumania",
                        "John Wick: Chapter 4",
                        "Sound of Freedom",
                    )

                MovieYear.Movie2022 ->
                    listOf(
                        "Black Panther: Wakanda Forever",
                        "Thor: Love and Thunder",
                        "Doctor Strange in the Multiverse of Madness",
                        "Halloween Ends",
                        "Avatar: The Way of Water",
                        "Jurassic World Dominion",
                        "The Batman",
                        "Scream",
                        "The Guardians of the Galaxy Holiday Special",
                        "Fantastic Beasts: The Secrets of Dumbledore",
                        "Uncharted",
                        "Minions: The Rise of Gru",
                    )

                MovieYear.Movie2021 ->
                    listOf(
                        "Spider-Man: No Way Home",
                        "Shang-Chi and the Legend of the Ten Rings",
                        "Venom: Let There Be Carnage",
                        "Black Widow",
                        "F9",
                        "Eternals",
                        "Sing 2",
                        "No Time to Die",
                        "A Quiet Place Part II",
                        "Ghostbusters: Afterlife",
                        "Dr. Bird's Advice for Sad Poets",
                    )

                MovieYear.Movie2020 -> {
                    listOf(
                        "Bad Boys for Life",
                        "Sonic the Hedgehog",
                        "Birds of Prey",
                        "Dolittle",
                        "The Invisible Man",
                        "The Call of the Wild",
                        "Onward",
                        "The Croods: A New Age",
                        "Tenet",
                        "Wonder Woman 1984",
                        "The Grudge",
                    )
                }
            }

        return moviesList
            .map { name ->
                Movies(
                    title = name,
                    subTitle = this.name,
                    icon = icon,
                )
            }.toCollection(ArrayList())
    }
}


