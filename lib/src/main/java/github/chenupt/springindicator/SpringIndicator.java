/*
 * Copyright 2015 chenupt
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

package github.chenupt.springindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenupt@gmail.com on 2015/1/31.
 * Description TODO
 */
public class SpringIndicator extends FrameLayout{

    private LinearLayout container;
    private SpringView springView;

    private List<View> tabs;

    public SpringIndicator(Context context) {
        this(context, null);
    }

    public SpringIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        tabs = new ArrayList<>();

        addPoints();
        container = new LinearLayout(getContext());
        container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setGravity(Gravity.CENTER);
        addView(container);
        addItems();
    }

    private void addPoints(){
        springView = new SpringView(getContext());
        addView(springView);
    }

    private void addItems(){
        for (int i = 0; i < 4; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(String.valueOf(i));
            textView.setTextSize(18);
            textView.setPadding(50, 0, 50, 0);
            tabs.add(textView);
            container.addView(textView);
        }
    }

    private void setHead(){
        View view = tabs.get(0);
        springView.getHeadPoint().setX(view.getX() + view.getWidth()/2);
        springView.getHeadPoint().setY(view.getY() + view.getHeight()/2);
    }

    private void setFoot(){
        View view = tabs.get(1);
        springView.getFootPoint().setX(view.getX() + view.getWidth()/2);
        springView.getFootPoint().setY(view.getY() + view.getHeight()/2);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setHead();
        setFoot();
    }
}
