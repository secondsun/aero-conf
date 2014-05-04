package net.saga.aeroconf.app.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.saga.aeroconf.app.R;
import net.saga.aeroconf.app.data.provider.DashboardCards;
import net.saga.aeroconf.app.data.vo.DashboardCard;
import net.saga.aeroconf.app.util.CachingImageProvider;

public class DashboardFragment extends Fragment {


    private final DashboardCard[] CARDS = DashboardCards.CARDS;
    private boolean stopped = false;

    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout v = (RelativeLayout) inflater.inflate(R.layout.dashboard, container, false);
        assert v != null;

        ListView cardList = (ListView) v.findViewById(R.id.cards_list_view);

        cardList.setAdapter(new ArrayAdapter<DashboardCard>(getActivity(), R.layout.dashboard_card, CARDS) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(getContext(), R.layout.dashboard_card, null);
                }

                DashboardCard card = CARDS[position];
                
                final ImageView img = (ImageView) convertView.findViewById(R.id.dashboard_card_image);
                final TextView text = (TextView) convertView.findViewById(R.id.dashboard_card_description);

                text.setText(card.text);

                CachingImageProvider.getInstance(getContext()).loadImage(Uri.parse(card.imageUrl.toString()), new CachingImageProvider.ImageLoaded() {
                    @Override
                    public void onImageLoad(Bitmap bitmap) {
                        if (!stopped) {
                            img.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
                        }
                    }
                });

                return convertView;
            }
        });

        return v;
    }


    @Override
    public void onStop() {
        super.onStop();
        stopped = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        stopped = false;
    }


}
