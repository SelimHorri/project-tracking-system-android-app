package com.selimhorri.pack.activity.admin.dept;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.activity.admin.AdminIndexActivity;
import com.selimhorri.pack.activity.admin.AdminInfoActivity;

public class AdminDepartmentAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_department);
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
                super.startActivity(new Intent(AdminDepartmentAddActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminCategories:
                super.startActivity(new Intent(this, AdminIndexActivity.class));
                return true;
            case R.id.adminNewDepartment:
                super.startActivity(new Intent(AdminDepartmentAddActivity.this, AdminDepartmentAddActivity.class));
                return true;
            case R.id.adminAbout:
                // super.startActivity(new Intent(AdminDepartmentAddActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminLogout:
                super.getSharedPreferences("admin", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(AdminDepartmentAddActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}