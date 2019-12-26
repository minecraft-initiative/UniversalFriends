package me.ironexception.universalfriends.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ironexception.universalfriends.TestUtil;
import me.ironexception.universalfriends.configuration.Configuration;
import me.ironexception.universalfriends.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FriendFileSaverTest {

    @Test
    @DisplayName("Saves identical configuration as input")
    public void testSave() throws URISyntaxException, IOException, FriendFileLoaderException {
        Path sourceFile = TestUtil.getPath("friends.json");

        Configuration<Person> configuration = FriendFileLoader.loader(Person.class)
                .withPath(sourceFile)
                .withDefaultFactory()
                .load();

        StringWriter writer = new StringWriter();
        FriendFileSaver.save(configuration, new Gson(), writer);
        String output = writer.toString();

        assertEquals(readFile(sourceFile).replaceAll("\\s", ""), output.replaceAll("\\s", "")); // remove all whitespace so indents and random newlines don't matter
    }

    private static String readFile(Path path) throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, Charset.defaultCharset());
    }

}