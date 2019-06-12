package com.paulomello.avaliacaoviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.TabClickListener;

public class MainActivity extends AppCompatActivity {

    private ImageView buttonNext;
    private ImageView buttonPrevious;
    private CustomViewPager viewPager;

    private int SIZE = 10;
    private SpringIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setComponents();
        setClicks();
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void checkItemPosition(boolean dir) {
        if (viewPager.getCurrentItem() > 0 || viewPager.getCurrentItem() < SIZE - 1) {
            if (dir) viewPager.setCurrentItem(getItem(+1), true);
            else viewPager.setCurrentItem(getItem(-1), true);
            setButtonVisibility();
        }
    }

    private void setButtonVisibility() {
        if (viewPager.getCurrentItem() == 0) {
            buttonPrevious.setVisibility(View.INVISIBLE);
            buttonNext.setVisibility(View.VISIBLE);
        } else if (viewPager.getCurrentItem() == SIZE - 1) {
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.VISIBLE);
        } else {
            buttonPrevious.setVisibility(View.VISIBLE);
            buttonNext.setVisibility(View.VISIBLE);
        }
    }

    private void setClicks() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkItemPosition(true);
            }
        });
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkItemPosition(false);
            }
        });
        indicator.setOnTabClickListener(new TabClickListener() {
            @Override
            public boolean onTabClick(int position) {
                viewPager.setCurrentItem(position);
                setButtonVisibility();
                return false;
            }
        });
    }

    private void setComponents() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setPagingEnabled(false);
        buttonNext = findViewById(R.id.imageNext);
        buttonPrevious = findViewById(R.id.imagePrevious);
        indicator = findViewById(R.id.indicator);

        for (int i = 0; i < SIZE; i++) {
            QuestionFragment fragment = new QuestionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("TEXT", "Questão " + (i + 1));
            fragment.setArguments(bundle);
            adapter.addFragment(fragment);
        }
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

        for (int i = 0; i < 10; i++)
            if (indicator.getTabs().get(i) != null)
                indicator.getTabs().get(i).setText(String.valueOf(i + 1));
    }


}
