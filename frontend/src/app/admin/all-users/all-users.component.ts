import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin-service/admin.service';
import { ProfileModel } from 'src/app/userprofile/profile-model';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {

  users: Array<ProfileModel> = [];

  constructor(private adminService: AdminService) {
    this.adminService.getUsers().subscribe(data => {
                            this.users = data;
                    });
  }
  ngOnInit(): void {

  }

  deleteUser(id: string): void{
      this.adminService.deleteUser(id).subscribe(data => {
                                                          this.users = data;
                                                  });
  }

}
