const apiBaseUrl = import.meta.env.VITE_API_BASE_URL

export async function get(): Promise<{body: any}> {
    console.log(`API URL: ${apiBaseUrl}`)

    const invoices = await (
        await fetch(`${apiBaseUrl}/invoices`)
    ).json();

    if (invoices) {
        console.debug(invoices)
        return {
            body: {invoices}
        };
    }

}