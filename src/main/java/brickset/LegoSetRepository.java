package brickset;

import repository.Repository;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns the number of LEGO sets with the tag specified.
     * @param tag a LEGO set tag
     * @return the number of LEGO sets with the tag specified
     */
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }

    /**
     * Returns the largest piece of the LEGO set.
     * @return the largest piece of the LEGO set
     */
    public int countLegoSetsLargestPiece() {
        return getAll()
                .stream()
                .mapToInt(LegoSet::getPieces)
                .max()
                .orElse(0);
    }

    /**
     * Prints the LEGO set names in alphabetical order.
     */
    public void printLegoSetsInAlphabeticalOrder() {
        getAll()
                .stream()
                .map(LegoSet::getName)
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }

    /**
     * Prints the names of the lego pieces between 100 and 500 in reverse order.
     */
    public void printNamesWithPiecesBetween100and500() {
        getAll()
                .stream()
                .filter(legoSet -> legoSet.getPieces() >= 100 && legoSet.getPieces() <= 500)
                .map(LegoSet::getName)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    /**
     * Prints the LEGO set name starts with letter B.
     */
    public void printNamesStartWithLetterB() {
        getAll()
                .stream()
                .map(LegoSet::getName)
                .filter(name -> name.charAt(0) == 'B')
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }

    /**
     * Returns the number of the packingType which is specified.
     * @return the number of the packingType which is specified
     */
    public long packagingTypeSpecified() {
        return getAll()
                .stream()
                .filter(legoSet -> !legoSet.getPackagingType().toString().equals("NOT_SPECIFIED"))
                .mapToInt(LegoSet::getPieces)
                .count();
    }

    /**
     * Returns the average piece for the specified theme.
     * @param theme a LEGO set theme
     * @return the average piece for the specified theme
     */
    public double averagePieces(String theme) {
        return getAll()
                .stream()
                .filter(legoSet -> Objects.equals(legoSet.getTheme(), theme))
                .mapToInt(LegoSet::getPieces)
                .average()
                .orElseThrow(IllegalStateException::new);
    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.countLegoSetsWithTag("Microscale"));

        System.out.println(repository.countLegoSetsLargestPiece());
        repository.printLegoSetsInAlphabeticalOrder();
        repository.printNamesWithPiecesBetween100and500();
        repository.printNamesStartWithLetterB();
        System.out.println(repository.packagingTypeSpecified());
        System.out.println(repository.averagePieces("Bionicle"));
    }

}