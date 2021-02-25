package v1.entities.global;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public enum Mod {
    NONE(0, "none", ""),
    NOFAIL(1, "no fail", "NF", "fm"),
    EASY(2, "easy", "EZ", "fm"),

    TOUCHDEVICE(4, "touch devices", "TD"),
    HIDDEN(8, "hidden", "HD", "fm", "sim"),
    HARDROCK(16,"hardrock", "HR", "fm", "sim"),

    SUDDENDEATH(32, "sudden death", "SD", "fm"),

    DOUBLETIME(64, "double time", "DT", "sim"),
    RELAX(128, "relax", "R", "fm"),
    HALFTIME(256, "half time", "HT"),
    NIGHTCORE(512, "night core", "NC"), // Only set along with DoubleTime. i.e: NC only gives 576
    FLASHLIGHT(1024, "flashlight", "FL", "fm", "sim"),
    AUTOPLAY(2048, "autoplay", "AP"),
    SPUNOUT(4096, "spun out", "SO", "fm"),

    RELAX2(8192, "autopilot", "AP", "fm"),    // Autopilot
    PERFECT(16384, "perfect", "PF"), // Only set along with SuddenDeath. i.e: PF only gives 16416

    KEY4(32768, "4 keys", "4K", "k", "fm"),
    KEY5(65536, "5 keys", "5K", "k", "fm"),
    KEY6(131072, "6 keys", "6K", "k", "fm"),
    KEY7(262144, "7 keys", "7K", "k", "fm"),
    KEY8(524288, "8 keys", "8K", "k", "fm"),

    FADEIN(1048576, "fade in", "FI", "fm", "sim"),
    RANDOM(2097152, "random", "R"),
    CINEMA(4194304, "cinema", "C"),
    TARGET(8388608, "target", "T"),

    KEY9(16777216, "9 keys", "9K", "k", "fm"),
    KEYCOOP(33554432, "key coop", "KC", "k", "fm"),
    KEY1(67108864, "1 key", "1K", "k", "fm"),
    KEY3(134217728, "3 keys", "3K", "k", "fm"),
    KEY2(268435456, "2 keys", "2K", "k", "fm"),

    SCOREV2(536870912, "score v2", "v2"),
    MIRROR(1073741824, "mirror", "M");




    private final int bitwise;
    private final String title;
    private final String abbreviation;
    private boolean isKeyMod = false;
    private boolean allowsFreeMod = false;
    private boolean isScoreIncreaseMod = false;

    Mod(int bitwise, String title, String abbreviation) {
        this.bitwise = bitwise;
        this.title = title;
        this.abbreviation = abbreviation;
    }

    Mod(int id, String title, String abbreviation, String... types) {
        this.bitwise = id;
        this.title = title;
        this.abbreviation = abbreviation;

        for (String type : types) {
            switch (type.toLowerCase()) {
                case "key", "k" -> isKeyMod = true;
                case "freemod", "fm" -> allowsFreeMod = true;
                case "scoreincreasemods", "sim" -> isScoreIncreaseMod = true;
            }
        }
    }

    public int getBitwise() {
        return bitwise;
    }

    public String getTitle() {
        return title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public boolean allowsFreeMod() {
        return allowsFreeMod;
    }

    public boolean isKeyMod() {
        return isKeyMod;
    }

    public boolean increasesScore() {
        return isScoreIncreaseMod;
    }

    public static ArrayList<Mod> getByBitwise(int bitwiseInt) {

        ArrayList<Mod> mods = new ArrayList<>();

        final AtomicInteger bitwise = new AtomicInteger(bitwiseInt);
        while (bitwise.get() != 0) {

            Arrays.stream(Mod.values())
                    .filter(mod -> !(mod.getBitwise() > bitwise.get()))
                    .reduce((first, second) -> second)
                    .ifPresent(mod -> {
                        bitwise.set(bitwise.get() - mod.getBitwise());
                        mods.add(mod);
                    });
        }

        return mods;
    }

    public static int getBitwiseFromMods(ArrayList<Mod> mods) {
        return mods.stream().mapToInt(Mod::getBitwise).sum();
    }

    @Override
    public String toString() {
        return "\n title='" + title + "' \n";
    }
}
