'use client';

import Header from "@/components/Header";
import Footer from "@/components/Footer";
import { usePathname } from 'next/navigation';

export default function AppLayoutWrapper({ children }) {
  const pathname = usePathname();
  const isAdmin = pathname.startsWith("/admin");

  return (
    <>
      {!isAdmin && <Header />}
      <main style={{ flex: 1 }}>
        {children}
      </main>
      <Footer />
    </>
  );
}
