export async function get(): Promise<{body: any}> {

    const invoices = await (
        await fetch("http://localhost:8080/invoices")
    ).json();

    if (invoices) {
        return {
            body: {invoices}
        };
    }

}