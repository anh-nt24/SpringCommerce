import MainLayout from "../../layouts/MainLayout";
import Content from "../../components/Content"

const Main = () => {
    return (
        <MainLayout className='Homepage'>
            {(filteredProducts) => <Content products={filteredProducts} />}
        </MainLayout>
    );
};




export default Main;