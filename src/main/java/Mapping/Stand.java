package Mapping;

public record Stand(String stand, String standUser, String destructivePower, String speed, String range, String stamina,
                    String precision, String developmentPotential) {

    @Override
    public String toString() {
        return "Stand: " + stand + "\n" +
                "Stand User: " + standUser + "\n" +
                "Destructive Power: " + destructivePower + "\n" +
                "Speed: " + speed + "\n" +
                "Range: " + range + "\n" +
                "Stamina: " + stamina + "\n" +
                "Precision: " + precision + "\n" +
                "Development Potential: " + developmentPotential + "\n";
    }
}
