package server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationUtilsTest {

    private static final String LOCATION = "path/path";

    @Test
    void shouldReturnLocationStartFromEndSlash() {
        assertEquals("/path", LocationUtils.endSlash(LOCATION));
    }

    @Test
    void shouldAddSlashToLocationStartWhenNotExists() {
        assertEquals("/path/path", LocationUtils.fixStartSlash(LOCATION));
    }

    @Test
    void shouldAddSlashToLocationEndWhenNotExists() {
        assertEquals("path/path/", LocationUtils.fixEndSlash(LOCATION));
    }
}
