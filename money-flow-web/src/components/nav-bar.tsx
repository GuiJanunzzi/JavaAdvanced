interface NavBarProps{
    active: "dashboard" | "movimentações" | "categorias"
}

export default function NavBar(props: NavBarProps) {
    const { active } = props
    const classActive = "border-b-3 border-pink-700"


  return(
    <nav className="flex justify-between items-center p-6 bg-slate-900">
        <h1 className="text-2xl font-bold">Money Flow</h1>
        <ul className="flex gap-4">
            <li className={active === "dashboard" ? classActive : ""}>
                <a href="/dashboard">dashboard</a>
            </li>
            <li className={active === "movimentações" ? classActive : ""}>
                <a href="/transactions">movimentações</a>
            </li>
            <li className={active === "categorias" ? classActive : ""}>
                <a href="/categories">categorias</a>
            </li>
        </ul>
        <img className="size-12 rounded-full" src="https://github.com/GuiJanunzzi.png" alt="Foto de usuário do github" />
    </nav>
  )
}
