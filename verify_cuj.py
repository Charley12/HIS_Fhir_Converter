from playwright.sync_api import sync_playwright

def run_cuj(page):
    page.goto("http://localhost:4173")
    page.wait_for_timeout(1000)

    # Click the modify button
    page.get_by_role("button", name="Modify").click()
    page.wait_for_timeout(500)

    # Click the approve button
    page.get_by_role("button", name="Approve").click()
    page.wait_for_timeout(500)

    # Type a message
    page.get_by_placeholder("Type your message...").fill("Hello AI, help me map this schema.")
    page.wait_for_timeout(500)

    # Click send
    page.get_by_role("button", name="Send").click()
    page.wait_for_timeout(500)

    # Expand/Collapse Tree Node
    page.get_by_text("Observation").click()
    page.wait_for_timeout(500)
    page.get_by_text("Observation").click()
    page.wait_for_timeout(500)

    # Take screenshot at the key moment
    page.screenshot(path="/home/jules/verification/screenshots/verification.png")
    page.wait_for_timeout(1000)  # Hold final state for the video

if __name__ == "__main__":
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        context = browser.new_context(
            record_video_dir="/home/jules/verification/videos"
        )
        page = context.new_page()
        try:
            run_cuj(page)
        finally:
            context.close()  # MUST close context to save the video
            browser.close()
