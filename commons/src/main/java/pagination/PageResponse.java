package pagination;

import builder.Builder;

import java.util.List;

public record PageResponse<T extends Page>(
        List<T> content,
        int numPage,
        int size,
        int totalElements,
        int totalPages
) {
    public static  <T extends Page> PageResponseBuilder<T> builder(){
        return new PageResponseBuilder<>();
    }

    public static class PageResponseBuilder<T extends Page> implements Builder<PageResponse<T>>
    {
        private List<T> content;
        private int numPage;
        private int size;
        private int totalElements;


        public PageResponseBuilder<T> content (List<T> content){
            this.content = content;
            return this;
        }
        public PageResponseBuilder<T> numPage (int numPage){
            this.numPage = numPage;
            return this;
        }
        public PageResponseBuilder<T> size (int size){
            this.size = size;
            return this;
        }
        public PageResponseBuilder<T> totalElements (int totalElements){
            this.totalElements = totalElements;
            return this;
        }


        @Override
        public PageResponse<T> build() {
            int computedTotalPages = size > 0
                    ? (int) Math.ceil((double) totalElements / size)
                    : 0;
            return new PageResponse<>(content, numPage, size, totalElements, computedTotalPages);
        }
    }

}
