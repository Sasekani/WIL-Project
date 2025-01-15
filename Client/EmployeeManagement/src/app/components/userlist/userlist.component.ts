import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UsersService } from '../../service/users.service';

@Component({
  selector: 'app-userlist',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './userlist.component.html',
  styleUrl: './userlist.component.css'
})
export class UserlistComponent implements OnInit {

  users: any[] = [];
  errorMessage: string = ''
  constructor(
    private readonly userService: UsersService,
    private readonly router: Router
  ) {}


  ngOnInit(): void {
    this.loadUsers();

  }

  async loadUsers() {
    try {
        const token: any = localStorage.getItem('token');
        console.log("Token:", token); // Log the token

        const response = await this.userService.getAllUsers(token);
        console.log("API Response:", response); // Log the entire response

        if (!response) {
            console.error("Response is null or undefined");
            this.showError("No response from the server.");
            return; // Important: Exit the function to prevent further errors
        }

        if (typeof response !== 'object') {
            console.error("Response is not an object. Type:", typeof response);
            this.showError("Invalid response format from the server.");
            return;
        }

        if (!response.ourUsersList) {
            console.error("response.ourUsersList is undefined.");
            this.showError("The 'ourUsersList' property is missing in the response.");
            return;
        }

        if (!Array.isArray(response.ourUsersList)) {
            console.error("response.ourUsersList is not an array. Type:", typeof response.ourUsersList);
            this.showError("The 'ourUsersList' property is not an array.");
            return;
        }

        this.users = response.ourUsersList;
    } catch (error: any) {
        console.error("Error during API call:", error);
        this.showError(error.message || 'An error occurred while fetching users.');
    }
}
  
  
  async deleteUser(userId: string) {
    const confirmDelete = confirm('Are you sure you want to delete this user?');
    if (confirmDelete) {
      try {
        const token: any = localStorage.getItem('token');
        await this.userService.deleteUser(userId, token);
        // Refresh the user list after deletion
        this.loadUsers();
      } catch (error: any) {
        this.showError(error.message);
      }
    }
  }

  navigateToUpdate(userId: string) {
    this.router.navigate(['/update', userId]);
  }
  navigateToPayEmployee() {
    this.router.navigate(['/salary']);
  }

  showError(message: string) {
    this.errorMessage = message;
    setTimeout(() => {
      this.errorMessage = ''; 
    }, 3000);
  }

}
