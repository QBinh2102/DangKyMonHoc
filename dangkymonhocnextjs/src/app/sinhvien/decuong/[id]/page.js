'use client'

import Apis, { endpoints } from "@/configs/Apis";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

const DeCuongMonHoc = () => {

    const {id} = useParams();
    const [monHoc, setMonHoc] = useState({});
    const [loading, setLoading] = useState(false);

    const loadMonHoc = async() => {
        setLoading(true);
        try{
            let res = await Apis.get(endpoints['monHocId'](id));
            setMonHoc(res.data);
        }catch(ex){
            console.error(ex);
        }finally{
            setLoading(false);
        }
    }

    useEffect(() => {
        loadMonHoc();
    }, [id]);

    return (
        <div>
            {loading?(
                <p>Đang tải dữ liệu...</p>
            ):(
                <div>
                    <h1>{monHoc}</h1>
                </div>
            )}
        </div>
    );
}
export default DeCuongMonHoc;