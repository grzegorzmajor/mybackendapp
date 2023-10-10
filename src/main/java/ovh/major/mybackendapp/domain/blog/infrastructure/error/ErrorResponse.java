package ovh.major.mybackendapp.domain.blog.infrastructure.error;

import org.springframework.http.HttpStatus;

record ErrorResponse(String message,
                            HttpStatus status) {
}
