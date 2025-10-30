package dto.response.succes;

import builder.Builder;

import java.time.Instant;

public record ApiSucces<T>(
        String message,
        int status,
        T data,
        String path,
        Instant timestamp) {

    public static <T> ApiSucces<T> ok(T data, String path){
        return new ApiSucces<>("OK", 200, data, path, Instant.now());
    }

    public static <T> ApiSucces<T> created(T data, String path){
        return new ApiSucces<>("Created", 201, data, path, Instant.now());
    }

    public static <T> ApiSuccesBuilder<T> builder() {
        return new ApiSuccesBuilder<>();
    }

    public static class ApiSuccesBuilder<T> implements Builder<ApiSucces<T>> {
        private String message;
        private int status;
        private T data;
        private String path;

        public ApiSuccesBuilder<T> message(String message){
            this.message = message;
            return this;
        }

        public ApiSuccesBuilder<T> status(int status){
            this.status = status;
            return this;
        }

        public ApiSuccesBuilder<T> data(T data){
            this.data = data;
            return this;
        }

        public ApiSuccesBuilder<T> path(String path){
            this.path = path;
            return this;
        }

        @Override
        public ApiSucces<T> build() {
            return new ApiSucces<>(message, status, data, path, Instant.now());
        }
    }
}
