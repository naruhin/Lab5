package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "http://www.omdbapi.com/?apikey=d3570ab3&";
        jsonGetter.start();

        System.out.println("Waiting for data...");
        String jsonString = jsonGetter.jsonIn;
        System.out.println(jsonString);


        // Считываем json
        Object obj = null;
        try
        {
            obj = new JSONParser().parse(jsonString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        System.out.println();

        JSONArray jsonArray = (JSONArray) obj;
        System.out.println(jsonArray.toJSONString());
        System.out.println();

        Films films = new Films();

        for (Object jsonObject : jsonArray)
        {

            JSONObject current = (JSONObject) jsonObject;
            String title = (String) current.get("title");
            String year = (String) current.get("year");
            String rated = (String) current.get("rated");
            String released = (String) current.get("released");
            String runtime = (String) current.get("runtime");
            String genre = (String) current.get("genre");
            String director = (String) current.get("director");
            String writer = (String) current.get("writer");
            String actors = (String) current.get("actors");
            String plot = (String) current.get("plot");
            String language = (String) current.get("language");
            String country = (String) current.get("country");
            String awards = (String) current.get("awards");
            String poster = (String) current.get("poster");
            List<Rating> ratings = (List<Rating>) current.get("ratings");
            String metascore = (String) current.get("metascore");
            String imdbRating = (String) current.get("imdbRating");
            String imdbVotes = (String) current.get("imdbVotes");
            String imdbID = (String) current.get("imdbID");
            String type = (String) current.get("type");
            String dVD = (String) current.get("dVD");
            String boxOffice = (String) current.get("boxOffice");
            String production = (String) current.get("production");
            String website = (String) current.get("website");
            String response = (String) current.get("response");

            Film film = new Film(title,year,rated,released,runtime,genre,director,writer,actors,plot,language,country,awards,poster,ratings,metascore,imdbRating,imdbVotes,imdbID,type,dVD,boxOffice,production,website,response);
            films.add(film);
        }

        System.out.println("Imported data after parsing:\n" + films);
        System.out.println(films);

        films.getFilms().sort(Film.byYearAsc);
        System.out.println("After sorting by year ascending:\n" + films);
        films.getFilms().sort(Film.byYearDesc);
        System.out.println("After sorting by year descending:\n" + films);

        films.getFilms().sort(Film.byReleaseAsc);
        System.out.println("After sorting by realese ascending:\n" + films);
        films.getFilms().sort(Film.byReleaseDesc);
        System.out.println("After sorting by realese ascending:\n" + films);



    }
}
