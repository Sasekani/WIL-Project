import { CanActivateFn, Router } from '@angular/router';
import { UsersService } from './service/users.service';
import { inject } from '@angular/core';

export const usersGuard: CanActivateFn = (route, state) => {
  const userService = inject(UsersService);
  const router = inject(Router);

  if (userService.isAuthenticated()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};

export const adminGuard: CanActivateFn = (route, state) => {
  const userService = inject(UsersService);
  const router = inject(Router);

  if (userService.isAdmin()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};

export const managerGuard: CanActivateFn = (route, state) => {
  const userService = inject(UsersService);
  const router = inject(Router);

  if (userService.isManager()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};

export const hrGuard: CanActivateFn = (route, state) => {
  const userService = inject(UsersService);
  const router = inject(Router);

  if (userService.isHr()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
