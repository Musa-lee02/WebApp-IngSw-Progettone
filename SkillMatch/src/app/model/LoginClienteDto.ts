import { Cliente } from "./Cliente"

export interface LoginClienteDto{
    token: string
    cliente: Cliente
}