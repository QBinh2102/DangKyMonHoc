'use client';

import { useRouter } from 'next/navigation';

const TrangChaoMung = () => {
  const router = useRouter();

  return (
    <div className='container d-flex flex-column align-items-center justify-content-center vh-100 text-center'>
      <h1 className='mb-3'>Đồ án ngành đăng ký môn học</h1>
      <button className='btn btn-primary' onClick={() => router.push('/dangnhap')}>
        Đăng nhập
      </button>
    </div>
  );
}
export default TrangChaoMung;