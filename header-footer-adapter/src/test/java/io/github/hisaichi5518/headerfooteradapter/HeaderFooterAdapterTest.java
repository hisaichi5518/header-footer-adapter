package io.github.hisaichi5518.headerfooteradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class HeaderFooterAdapterTest {

    @Test
    public void testSetHeader() {
        DummyAdapter adapter = new DummyAdapter();
        adapter.setHeaderView(new View(RuntimeEnvironment.application));

        int position = 0;
        assertThat(adapter.isHeader(position)).isTrue();
        assertThat(adapter.getHeadersCount()).isEqualTo(1);
        assertThat(adapter.isHeaderOrFooter(position)).isTrue();
        assertThat(adapter.getItemViewType(position)).isEqualTo(HeaderFooterAdapter.ITEM_VIEW_TYPE_HEADER);
        assertThat(adapter.getItemCount()).isEqualTo(11);

        assertThat(adapter.isFooter(position)).isFalse();
        assertThat(adapter.getFootersCount()).isEqualTo(0);
    }

    @Test
    public void testSetFooter() {
        DummyAdapter adapter = new DummyAdapter();
        adapter.setFooterView(new View(RuntimeEnvironment.application));

        int position = adapter.getAdapterItemCount() + 1;
        assertThat(adapter.isFooter(position)).isTrue();
        assertThat(adapter.getFootersCount()).isEqualTo(1);
        assertThat(adapter.isHeaderOrFooter(position)).isTrue();
        assertThat(adapter.getItemViewType(position)).isEqualTo(HeaderFooterAdapter.ITEM_VIEW_TYPE_FOOTER);
        assertThat(adapter.getItemCount()).isEqualTo(11);

        assertThat(adapter.isHeader(position)).isFalse();
        assertThat(adapter.getHeadersCount()).isEqualTo(0);
    }

    @Test
    public void testOnCreateViewHolder_Header() {
        DummyAdapter adapter = new DummyAdapter();
        adapter.setHeaderView(new View(RuntimeEnvironment.application));
        adapter.setFooterView(new View(RuntimeEnvironment.application));

        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(
                null, HeaderFooterAdapter.ITEM_VIEW_TYPE_HEADER);

        assertThat(holder).isInstanceOf(HeaderFooterAdapter.HeaderFooterHolder.class);
    }

    @Test
    public void testOnCreateViewHolder_Footer() {
        DummyAdapter adapter = new DummyAdapter();
        adapter.setHeaderView(new View(RuntimeEnvironment.application));
        adapter.setFooterView(new View(RuntimeEnvironment.application));

        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(
                null, HeaderFooterAdapter.ITEM_VIEW_TYPE_FOOTER);

        assertThat(holder).isInstanceOf(HeaderFooterAdapter.HeaderFooterHolder.class);
    }

    @Test
    public void testOnCreateViewHolder_Item() {
        DummyAdapter adapter = new DummyAdapter();
        adapter.setHeaderView(new View(RuntimeEnvironment.application));
        adapter.setFooterView(new View(RuntimeEnvironment.application));

        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(
                null, HeaderFooterAdapter.ITEM_VIEW_TYPE_ITEM);

        assertThat(holder).isInstanceOf(DummyViewHolder.class);
    }

    @Test
    public void testOnBindViewHolder() {
        final AtomicBoolean called = new AtomicBoolean();
        DummyAdapter adapter = new DummyAdapter() {
            @Override
            protected void onBindItemViewHolder(DummyViewHolder holder, int position) {
                assertThat(holder).isInstanceOf(DummyViewHolder.class);
                assertThat(position).isEqualTo(1);
                called.set(true);
            }
        };
        adapter.setHeaderView(new View(RuntimeEnvironment.application));
        adapter.setFooterView(new View(RuntimeEnvironment.application));

        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(
                null, HeaderFooterAdapter.ITEM_VIEW_TYPE_ITEM);

        adapter.onBindViewHolder(holder, 2);
        assertThat(called.get()).isTrue();
    }

    class DummyViewHolder extends RecyclerView.ViewHolder {
        public DummyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class DummyAdapter extends HeaderFooterAdapter<DummyViewHolder> {
        @Override
        protected DummyViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
            return new DummyViewHolder(new View(RuntimeEnvironment.application));
        }

        @Override
        protected void onBindItemViewHolder(DummyViewHolder holder, int position) {
        }

        @Override
        protected int getAdapterItemCount() {
            return 10;
        }
    }
}

