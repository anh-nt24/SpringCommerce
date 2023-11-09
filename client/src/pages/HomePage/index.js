import MainLayout from "../../layouts/MainLayout";
import Content from "../../components/Content"
import { useEffect } from "react";

const Main = () => {
    useEffect(() => {
        document.title = 'Shop - Homepage';
    }, []);
    return (
        <MainLayout className='Homepage'>
            <Content />
        </MainLayout>
    );
};




export default Main;