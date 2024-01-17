import { Role } from "src/app/core/models/role"

export type DecodedToken={
  sub: string,
  role: {id:number, roleName:Role}[],
  verified: boolean,
  iat: number,
  exp: number
}
