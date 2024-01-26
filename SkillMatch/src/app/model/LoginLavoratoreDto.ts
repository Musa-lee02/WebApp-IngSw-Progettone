import { Lavoratore } from "./Lavoratore"

export interface LoginLavoratoreDto{
    token: string
    lavoratore: Lavoratore
}