"use client"
import { createCategory } from "@/actions/category-actions";
import NavBar from "@/components/nav-bar";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import Link from "next/link";
import { useActionState } from "react";

const initialState = {
  values:{
    name:"",
    icon:""
  }
}

export default function CategoryFormPage() {
  const [state, formAction, pending] = useActionState(createCategory, initialState)

  return (
    <>
        <NavBar active="categorias"/>
        <main className="flex justify-center">
            <div className="bg-slate-900 p-6 m-6 rounded min-w-1/3">
                <h2>Cadastrar Categoria</h2>

                <form action={formAction}>
                  <Input name="name" placeholder="nome"/>
                  <Input name="icon" placeholder="icone"/>

                  <div>
                    <Button variant="outline" asChild>
                      <Link href="/categories">
                        Cancelar
                      </Link>
                    </Button>
                    <Button>Salvar</Button>
                  </div>
                </form>
            </div>
        </main>
    </>
  )
}
