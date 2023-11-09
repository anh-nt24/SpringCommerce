import NavBar from "../components/NavBar";

const MainLayout = ({children, page}) => {
    return (
        <>
            {!page && <NavBar/>}
            <div>
                {children}
            </div>
            <footer className="p-4 footer">
                <div className="text-center">
                © Copyright fakeshopee.com 2023. All rights reserved.
                </div>
            </footer>
        </>
    );
}

export default MainLayout;