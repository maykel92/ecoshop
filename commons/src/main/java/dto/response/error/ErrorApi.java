package dto.response.error;

import builder.Builder;

import java.time.Instant;
import java.util.List;

public record ErrorApi(
        TypeError typeError,
        String message,
        List<String>messages,
        String patch,
        int status,
        Instant timeStamp) {

    public static ErrorApiBuilder builder(){
        return new ErrorApiBuilder();
    }

    public static class ErrorApiBuilder implements Builder<ErrorApi> {
        private TypeError typeError;
        private String message;
        private List<String> messages = null;
        private String patch;
        private int status;

        public ErrorApiBuilder typeError(TypeError typeError) {
            this.typeError =typeError;
            return this;
        }

        public ErrorApiBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorApiBuilder messages(List<String> messages) {
            this.messages = messages;
            return this;
        }

        public ErrorApiBuilder patch(String patch) {
            this.patch = patch;
            return this;
        }

        public ErrorApiBuilder status(int status) {
            this.status = status;
            return this;
        }

        @Override
        public ErrorApi build(){
            return new ErrorApi(typeError, message, messages, patch, status, Instant.now());
        }
    }

}



