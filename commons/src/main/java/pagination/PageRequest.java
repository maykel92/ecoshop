package pagination;

public record PageRequest(
        int page,
        int size
) implements Pageable {

    @Override
    public int offSet() {
        return (page - 1) * size;
    }

}
