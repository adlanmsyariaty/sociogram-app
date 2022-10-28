import { Injectable, CanActivate, ExecutionContext } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Observable } from 'rxjs';
import { JwtService } from '@nestjs/jwt';
import { User } from '../users/user.model';
import { Model } from 'mongoose';
import { resolve } from 'path';

@Injectable()
export class AuthGuard implements CanActivate {
  private users: User[] = [];

  constructor(@InjectModel('User') private readonly userModel: Model<User>, private jwtService: JwtService) {}

  canActivate(
    context: ExecutionContext,
  ): boolean | Promise<boolean> | Observable<boolean> {
    const req = context.switchToHttp().getRequest();
    const { access_token } = req.headers
    let payload: any = {}
    console.log(access_token)
    return new Promise((resolve) => {
      payload = this.jwtService.verify(access_token)
      console.log(payload)
      resolve(this.userModel.findOne({ _id: payload.id }))
    })
    .then((res: any) => {
      console.log(res)
      if (res) {
        req.user = {
          id: res._id,
          name: res.name,
          username: res.username,
        }
        return true
      } else {
        return false
      }
    })
    .catch((err) => {
      return false
    })
  }
}