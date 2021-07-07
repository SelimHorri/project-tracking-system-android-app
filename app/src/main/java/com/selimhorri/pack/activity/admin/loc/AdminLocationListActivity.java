package com.selimhorri.pack.activity.admin.loc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.activity.admin.AdminIndexActivity;
import com.selimhorri.pack.activity.admin.AdminInfoActivity;
import com.selimhorri.pack.pattern.adapter.admin.AdminLocationListCustomAdapter;
import com.selimhorri.pack.service.LocationService;
import com.selimhorri.pack.service.impl.LocationServiceImpl;

public class AdminLocationListActivity extends AppCompatActivity {

    private final LocationService locationService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public AdminLocationListActivity() {
        this.locationService = new LocationServiceImpl(AdminLocationListActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_locations);

        this.initialize();

        this.locationService.findAll(
                response ->
                    this.recyclerView.setAdapter(
                            new AdminLocationListCustomAdapter(response.getCollection(), AdminLocationListActivity.this)
                    ),
                error -> Toast.makeText(AdminLocationListActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

    }

    private void initialize() {
        this.recyclerView = super.findViewById(R.id.recyclerViewAdminListLocation);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(AdminLocationListActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater()
                .inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.adminAccountInfo:
                super.startActivity(new Intent(AdminLocationListActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminCategories:
                super.startActivity(new Intent(this, AdminIndexActivity.class));
                return true;
            case R.id.adminNewLocation:
                super.startActivity(new Intent(AdminLocationListActivity.this, AdminLocationAddActivity.class));
                return true;
            case R.id.adminAbout:
                // super.startActivity(new Intent(AdminLocationAddActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminLogout:
                super.getSharedPreferences("admin", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(AdminLocationListActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}