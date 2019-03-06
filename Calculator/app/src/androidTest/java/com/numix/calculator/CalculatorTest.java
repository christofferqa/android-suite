/**
 * Copyright (c) 2008, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.numix.calculator;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CalculatorTest {

    @Rule
    public ActivityTestRule<Calculator> activityRule =
            new ActivityTestRule<>(Calculator.class);

    @Test
    public void testPressSomeKeys() {
        // Make sure that we clear the output
        onView(withId(R.id.clear)).perform(click());

        // 3 + 4 * 5 => 23
        onView(withId(R.id.display)).perform(pressKey(KeyEvent.KEYCODE_3));
        onView(withId(R.id.display)).perform(pressKey(KeyEvent.KEYCODE_PLUS));
        onView(withId(R.id.display)).perform(pressKey(KeyEvent.KEYCODE_4));
        onView(withId(R.id.display)).perform(pressKey(KeyEvent.KEYCODE_9 | KeyEvent.META_SHIFT_ON));
        onView(withId(R.id.display)).perform(pressKey(KeyEvent.KEYCODE_5));
        onView(withId(R.id.display)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withId(R.id.display)).check(matches(hasDescendant(withText("23"))));
    }
}
