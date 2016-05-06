package org.schabi.newpipe.extractor.youtube;

import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.Assert;

import org.json.JSONException;
import org.schabi.newpipe.Downloader;
import org.schabi.newpipe.extractor.AbstractVideoInfo;
import org.schabi.newpipe.extractor.ExtractionException;
import org.schabi.newpipe.extractor.ParsingException;
import org.schabi.newpipe.extractor.ServiceList;
import org.schabi.newpipe.extractor.StreamExtractor;
import org.schabi.newpipe.extractor.VideoStream;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Christian Schabesberger on 30.12.15.
 *
 * Copyright (C) Christian Schabesberger 2015 <chris.schabesberger@mailbox.org>
 * YoutubeVideoExtractorDefault.java is part of NewPipe.
 *
 * NewPipe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NewPipe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NewPipe.  If not, see <http://www.gnu.org/licenses/>.
 */

public class NewPipeSearchEngineTest extends AndroidTestCase {


    String testUrl1 = "https://www.youtube.com/watch?v=bbIlLmCID5g";
    String testUrl2 = "https://www.youtube.com/watch?v=pDxn0Xfqkgw";
    String testUrl3 = "https://www.youtube.com/watch?v=vxuZuXPouqM";
    String testUrl4 = "https://www.youtube.com/watch?v=SeQFicYc1as";
    String testUrl5 = "https://www.youtube.com/watch?v=AJBycnafu0I";
    private StreamExtractor extractor;

    public void setUp() throws IOException, ExtractionException, JSONException {
        extractor = ServiceList.getService("Youtube")
                .getExtractorInstance("https://www.youtube.com/watch?v=YQHsXMglC9A", new Downloader());
    }

    // Testing Postive test case Scenarios.
    public void testGeturlsPositive() throws ParsingException, IOException, JSONException {

        ArrayList<String> urls  = prepareTestData();

        assertTrue(extractor.geturls(urls) != null);
        assertTrue(extractor.geturls(urls).contains(","));
        assertTrue(extractor.geturls(urls).length() > testUrl1.length());
        assertTrue(extractor.geturls(urls).length() > testUrl2.length());
        assertTrue(extractor.geturls(urls).length() > testUrl3.length());
        assertTrue(extractor.geturls(urls).length() > testUrl4.length());
        assertTrue(extractor.geturls(urls).length() > testUrl5.length());
    }

    // Testing Negative Test case scenario.
    // As we are passing empty list we will get IndexOutOfBoundsException.
    public void testGeturlsNegative() throws ParsingException, IOException, JSONException {

        ArrayList<String> urls = new ArrayList<String>();

        try {
            extractor.geturls(urls);
            Assert.fail("IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    // Testing Negative Test case scenario.
    // As we are NULL to the main method we will get NullPointerException.
    public void testGeturlsNullValue() throws ParsingException, IOException, JSONException {

        ArrayList<String> urls = null;

        try {
            extractor.geturls(null);
            Assert.fail("NullPointerException");
        }
        catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    // Testing Negative Test case scenario.
    // As we are passing test urls with wrong format we will get ParseException
    public void testGeturlsWrongUlrFormat() throws ParsingException, IOException, JSONException {

        ArrayList<String> urls = new ArrayList<String>();

        urls.add("a");
        urls.add("b");
        urls.add("c");
        urls.add("d");
        urls.add("e");

        try {
            assertTrue(extractor.geturls(urls) != null);
            Assert.fail("ParsingException");
        }
        catch (ParsingException e) {
            assertTrue(true);
        }

    }


    // Create test data.
    private ArrayList<String> prepareTestData() {

        ArrayList<String> urls = new ArrayList<String>();

        urls.add(testUrl1);
        urls.add(testUrl2);
        urls.add(testUrl3);
        urls.add(testUrl4);
        urls.add(testUrl5);

        return urls;

    }
}
