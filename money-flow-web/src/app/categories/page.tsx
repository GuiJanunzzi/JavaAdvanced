import CategoryItem from "@/components/category-item";
import NavBar from "@/components/nav-bar";
import { Button } from "@/components/ui/button";
import { Plus } from "lucide-react";

async function getCategories(){
    const response = await fetch("http://localhost:8080/categories")
    return await response.json()
}

export default async function CategoriesPage() {

    const data : Category[] = await getCategories()

    return (
    <>
        <NavBar active="categorias"/>
        <main className="flex justify-center">
            <div className="bg-slate-900 p-6 m-6 rounded min-w-1/3">
                <div className="flex justify-between">
                    <h2 className="text-lg font-semibold">Categorias</h2>
                    <Button>
                        <Plus/>
                        Nova Categoria
                    </Button>
                </div>

                {data.map(category => <CategoryItem key={category.id} category={category}/>)}

            </div>
        </main>
    </>
  )
}
