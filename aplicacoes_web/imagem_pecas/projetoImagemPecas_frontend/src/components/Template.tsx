interface TemplateProps {
    children: React.ReactNode;
}

export const Template = ({children}: TemplateProps) => {
    return (
        <>
            <Header />

            {children}

            <h1>Footer</h1>
        </>
    )
}

const Header = () => {
    return (
        <header className='bg-red-600 text-white py-3'>
            <div className='container mx-auto flex items-center justify-between px-4'>
                <h1 className='text-3x1 font-bold'>Imagens peças</h1>
            </div>
        </header>
    )
}