import { Role } from "src/app/core/models/role";

export type User = {
  username: string,
  role: Role,
  isVerified: boolean,
  readonly accessToken: string
};
