package com.gildedrose;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class GoldenMasterTest {

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog().mute();

    @Before
    public void setUp() throws Exception {
        systemOutRule.clearLog();
    }

    @Test
    @Ignore("Unignore when need for re-generation")
    public void generate_golden_master() throws IOException {
        // Given
        File goldenMasterFile = Paths.get("src/test/resources/golden_master.txt").toFile();

        goldenMasterFile.delete();
        goldenMasterFile.createNewFile();

        // When
        TexttestFixture.main(new String[]{});


        // Then
        FileUtils.write(goldenMasterFile, systemOutRule.getLog());
    }

    @Test
    public void should_contain_same_output_as_older() throws IOException {
        // Given
        File goldenMasterFile = Paths.get("src/test/resources/golden_master.txt").toFile();


        // When
        TexttestFixture.main(new String[]{});

        // Then
        assertThat(FileUtils.readFileToString(goldenMasterFile)).isEqualTo(systemOutRule.getLog());
    }
}
