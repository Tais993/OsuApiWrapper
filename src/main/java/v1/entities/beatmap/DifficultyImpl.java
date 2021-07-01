package v1.entities.beatmap;

import com.google.gson.JsonObject;

public record DifficultyImpl(
        double starRating,
        double diffAim,
        double diffSpeed,
        double circleSize,
        double overallDifficulty,
        double approachRate,
        double healthDrain) implements IDifficulty {


    protected static DifficultyImpl ofJson(JsonObject json) {
        double starRating = json.get("difficultyrating").getAsDouble();
        double diffAim = json.get("diff_aim").getAsDouble();
        double diffSpeed = json.get("diff_speed").getAsDouble();
        double circleSize  = json.get("diff_size").getAsDouble();


        double overallDifficulty = json.get("diff_overall").getAsDouble();
        double approachRate = json.get("diff_approach").getAsDouble();
        double healthDrain = json.get("diff_drain").getAsDouble();

        return new DifficultyImpl(starRating, diffAim, diffSpeed, circleSize, overallDifficulty, approachRate, healthDrain);
    }
}
