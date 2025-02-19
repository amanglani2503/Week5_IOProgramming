package com.ioprogramming.day2.iplcensorship;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IPLCensorship {

    private static String maskTeamName(String team) {
        String[] words = team.split(" ");
        if (words.length > 1) {
            return words[0] + " ***";
        }
        return team;
    }

    private static String redactPlayerName(String player) {
        return "REDACTED";
    }

    public static void processJson(String inputFilePath, String outputFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(inputFilePath));

        ArrayNode censoredArray = objectMapper.createArrayNode();

        for (JsonNode match : rootNode) {
            ObjectNode censoredMatch = objectMapper.createObjectNode();

            censoredMatch.put("match_id", match.get("match_id").asInt());
            censoredMatch.put("team_1", maskTeamName(match.get("team_1").asText()));
            censoredMatch.put("team_2", maskTeamName(match.get("team_2").asText()));
            censoredMatch.put("winner", maskTeamName(match.get("winner").asText()));
            censoredMatch.put("player_of_the_match", redactPlayerName(match.get("player_of_the_match").asText()));
            censoredMatch.put("venue", match.get("venue").asText());
            censoredMatch.put("date", match.get("date").asText());

            censoredArray.add(censoredMatch);
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath), censoredArray);
        System.out.println("✅ Censored JSON saved at: " + outputFilePath);
    }

    public static void processCsv(String inputFilePath, String outputFilePath) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath))) {

            List<String[]> allData = reader.readAll();
            List<String[]> censoredData = new ArrayList<>();

            // Process header
            censoredData.add(allData.get(0));

            // Process rows
            for (int i = 1; i < allData.size(); i++) {
                String[] row = allData.get(i);
                row[1] = maskTeamName(row[1]); // Team 1
                row[2] = maskTeamName(row[2]); // Team 2
                row[3] = maskTeamName(row[3]); // Winner
                row[4] = redactPlayerName(row[4]); // Player of the Match
                censoredData.add(row);
            }

            writer.writeAll(censoredData);
            System.out.println("Censored CSV saved at: " + outputFilePath);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            String inputJson = "E:\\Files\\Week5\\day2\\IPL.json";
            String outputJson = "E:\\Files\\Week5\\day2\\CensoredIPL.json";

            String inputCsv = "E:\\Files\\Week5\\day2\\IPL.csv";
            String outputCsv = "E:\\Files\\Week5\\day2\\CensoredIPL.csv";

            processJson(inputJson, outputJson);
            processCsv(inputCsv, outputCsv);

            System.out.println("Censorship complete!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
