import NavBar from "@/components/nav-bar";

export default function TransactionsPage() {
  return (
    <>
        <NavBar active="movimentações"/>
        <main className="flex justify-center">
            <div className="bg-slate-900 p-6 m-6 rounded min-w-1/3">
                <h2>Movimentações</h2>
            </div>
        </main>
    </>
  )
}
