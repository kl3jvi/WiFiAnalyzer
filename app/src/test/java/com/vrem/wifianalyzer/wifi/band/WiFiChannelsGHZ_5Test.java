/*
 *    Copyright (C) 2015 - 2016 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.vrem.wifianalyzer.wifi.band;

import android.support.v4.util.Pair;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class WiFiChannelsGHZ_5Test {
    private WiFiChannels fixture;

    @Before
    public void setUp() throws Exception {
        fixture = WiFiChannels.makeGHZ_5();
    }

    @Test
    public void testFindWiFiChannel() throws Exception {
        assertEquals(36, fixture.findWiFiChannel(5180).getChannel());
        assertEquals(38, fixture.findWiFiChannel(5190).getChannel());
        assertEquals(165, fixture.findWiFiChannel(5825).getChannel());
    }

    @Test
    public void testFindWiFiChannelInRange() throws Exception {
        assertEquals(36, fixture.findWiFiChannelInRange(5178).getChannel());
        assertEquals(36, fixture.findWiFiChannelInRange(5182).getChannel());
    }

    @Test
    public void testFindWiFiChannelFail() throws Exception {
        assertEquals(WiFiChannel.UNKNOWN, fixture.findWiFiChannel(5179));
        assertEquals(WiFiChannel.UNKNOWN, fixture.findWiFiChannel(5828));
    }

    @Test
    public void testGetWiFiChannelFirst() throws Exception {
        assertEquals(36, fixture.getWiFiChannelFirst().getChannel());
    }

    @Test
    public void testGetWiFiChannelLast() throws Exception {
        assertEquals(165, fixture.getWiFiChannelLast().getChannel());
    }

    @Test
    public void testGetFrequencySpread() throws Exception {
        assertEquals(5, fixture.getFrequencySpread());
    }

    @Test
    public void testGetFrequencyOffset() throws Exception {
        assertEquals(20, fixture.getFrequencyOffset());
    }

    @Test
    public void testGetChannelsSet() throws Exception {
        assertEquals(3, fixture.getChannelsSet().size());
        validatePair(36, 64, 0);
        validatePair(100, 140, 1);
        validatePair(149, 165, 2);
    }

    private void validatePair(int expectedFirst, int expectedSecond, int index) {
        Pair<WiFiChannel, WiFiChannel> pair = fixture.getChannelsSet().get(index);
        assertEquals(expectedFirst, pair.first.getChannel());
        assertEquals(expectedSecond, pair.second.getChannel());
    }

    @Test
    public void testGetAvailableChannels() throws Exception {
        assertEquals(WiFiChannels.CHANNELS_GHZ_5.length, fixture.getAvailableChannels(Locale.US).size());
        assertEquals(WiFiChannels.CHANNELS_GHZ_5.length, fixture.getAvailableChannels(Locale.UK).size());
        assertEquals(WiFiChannels.CHANNELS_GHZ_5.length, fixture.getAvailableChannels(Locale.JAPAN).size());
    }
}